/**
 * $RCSfile$
 * $Revision: 1680 $
 * $Date: 2005-07-22 17:49:46 -0300 (Fri, 22 Jul 2005) $
 *
 * Copyright (C) 2004 Jive Software. All rights reserved.
 *
 * This software is published under the terms of the GNU Public License (GPL),
 * a copy of which is included in this distribution.
 */

package org.jivesoftware.wildfire.plugin.presence;

import org.jivesoftware.wildfire.XMPPServer;
import org.jivesoftware.wildfire.user.User;
import org.jivesoftware.wildfire.user.UserNotFoundException;
import org.xmpp.packet.JID;
import org.xmpp.packet.PacketError;
import org.xmpp.packet.Presence;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The XMLPresenceProvider provides information about the users presence in XML format.
 * The returned XML will include the last known presence of the user. If the user is offline
 * then the unavailable presence will be recreated with the last known presence status.
 *
 * @author Gaston Dombiak
 */
class XMLPresenceProvider extends PresenceInfoProvider {

    public void sendInfo(HttpServletRequest request, HttpServletResponse response,
            Presence presence) throws IOException {
        response.setContentType("text/xml");
        PrintWriter out = response.getWriter();
        if (presence == null) {
            // Recreate the unavailable presence with the last known status
            JID targetJID = new JID(request.getParameter("jid"));
            presence = new Presence(Presence.Type.unavailable);
            XMPPServer server = XMPPServer.getInstance();
            try {
                User user = server.getUserManager().getUser(targetJID.getNode());
                String status = server.getPresenceManager().getLastPresenceStatus(user);
                if (status != null) {
                    presence.setStatus(status);
                }
            }
            catch (UserNotFoundException e) {}
            presence.setFrom(targetJID);
        }
        out.println(presence.toXML());
        out.flush();
    }

    public void sendUserNotFound(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/xml");
        PrintWriter out = response.getWriter();
        // Send a forbidden presence
        Presence presence = new Presence();
        presence.setError(PacketError.Condition.forbidden);
        try {
            presence.setFrom(new JID(request.getParameter("jid")));
        }
        catch (Exception e) {}
        try {
            presence.setTo(new JID(request.getParameter("req_jid")));
        }
        catch (Exception e) {}
        out.println(presence.toXML());
        out.flush();
    }
}
