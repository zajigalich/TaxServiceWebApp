package com.my.web.controller.command.inspector;

import com.my.exception.UserNotFoundException;
import com.my.service.UserService;
import com.my.web.controller.command.Command;
import com.my.web.dto.UserDTO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class InspectorUserViewCommand implements Command {

    private static final Logger log = Logger.getLogger(InspectorReportsCommand.class);

    private final UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        log.debug("UserId: " + request.getParameter("userId"));
        try {
            UserDTO userDTO = userService.getUserInformationById(Long.parseLong(request.getParameter("userId")));
            log.debug(userDTO);
            request.setAttribute("userDTO", userDTO);
        } catch (NumberFormatException e) {
            request.setAttribute("errorInvalidParam", "Invalid parameter");
        } catch (UserNotFoundException e) {
            request.setAttribute("noUserFoundException", e.getMessage());
        }

        return "/WEB-INF/inspector/user-view";
    }
}
