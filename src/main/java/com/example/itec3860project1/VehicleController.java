package com.example.itec3860project1;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class VehicleController {

    ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public Vehicle addVehicle(@RequestBody Vehicle newVehicle) throws IOException {
        String json = mapper.writeValueAsString(newVehicle);
        FileUtils.writeStringToFile(new File("./vehicles.txt"), json + "\n", "UTF-8", true);
        return newVehicle;
    }

    @RequestMapping(value = "/getVehicle/{id}", method = RequestMethod.GET)
    public Vehicle getVehicle(@PathVariable int id) throws IOException {
        List<String> lines = FileUtils.readLines(new File("./vehicles.txt"), "UTF-8");
        for (String line : lines) {
            Vehicle v = mapper.readValue(line, Vehicle.class);
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }

    @RequestMapping(value = "/updateVehicle", method = RequestMethod.PUT)
    public Vehicle updateVehicle(@RequestBody Vehicle newVehicle) throws IOException {
        List<String> lines = FileUtils.readLines(new File("./vehicles.txt"), "UTF-8");
        for (String line : lines) {
            Vehicle v = mapper.readValue(line, Vehicle.class);
            if (v.getId() == newVehicle.getId()) {
                FileUtils.writeStringToFile(new File("./vehicles.txt"), "","UTF-8", false);
            }
        }

        for (String line : lines) {
            FileUtils.writeStringToFile(new File("./vehicles.txt"), line + "\n","UTF-8", true);
        }
        return null;
    }


}
