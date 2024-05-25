package splitwise.project.splitwise.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import splitwise.project.splitwise.DTO.GroupDTO;
import splitwise.project.splitwise.Model.Group;
import splitwise.project.splitwise.Model.User;
import splitwise.project.splitwise.Services.GroupService;
import splitwise.project.splitwise.Services.UserService;

@Controller
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public ResponseEntity<Group> createGroup(@RequestBody GroupDTO groupDTO){
        Group group=groupService.createGroup(groupDTO);
        if(group!=null){
            return ResponseEntity.ok(group);
        }return ResponseEntity.notFound().build();
    }
    @PostMapping("/addUsers/{groupId}")
    public ResponseEntity<List<User>> addUsersToGroup(@PathVariable long groupId,@RequestBody List<Long> userIds){
        Group group=groupService.addUsersToGroup(groupId, userIds);
        return ResponseEntity.ok(group.getGroupMembers());
    }

    @GetMapping("/create")
    public String createGroup(Model model){
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "Home/createGroup";
    }
    
}
