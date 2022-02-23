package com.assement.featureswtiches;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class httpWeb {

    private static int ids = 1;
    @Autowired
    private final repositories resp;

    httpWeb(repositories resp){
        this.resp = resp;
    }

    @GetMapping("/")
    public String index(){
        return "Hello, please go for feature";
    }

    @GetMapping("/feature")
    public Map<String,Boolean> getFeatures(@RequestParam String email, @RequestParam String featureName){
        features f = resp.findByEmail(email);
        HashMap<String,Boolean> map = new HashMap<>();
        if (f != null){
            if (featureName.equals(f.featureName)){
                map.put("canAccess", Boolean.valueOf(f.enable));
            }
            else{
                map.put("canAccess", false);
            }
        }
        else{
            map.put("Invalid Parameters", false);
        }
        return map;
/*0
        features f = resp2.findByEmail(email);
        if (f != null && f.feature == featureName){
            return "canAccess:" + f.enable;
        }
        else{
            return "Email not in databse";
        }
*/
    }

    @PostMapping(path = "/feature", consumes = "application/json", produces = "application/json")
    public ResponseEntity postFeatures(@RequestBody features f) {

 /*
        features f = new features();
        f.email = email;
        f.feature = featureName;
        f.enable = enable == "true"?true:false;
        System.out.println(f);

 */
       features check = resp.findByEmail(f.email);
        if (check != null && check.email.equals(f.email) && check.featureName.equals(f.featureName)) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED.ordinal()).body("Not Modified");
        }
        else {
            f.ID = ids;
            ids++;
            resp.save(f);
            return ResponseEntity.status(HttpStatus.OK).body("");
        }
    }

}
