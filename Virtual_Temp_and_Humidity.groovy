/**
 *
 */

metadata {
  definition (name: "Virtual Temperature & Humidity Sensor", namespace: "Abhijeet Ghosh", author: "Abhijeet Ghosh", mnmn: "SmartThings", vid: "generic-humidity", ocfDeviceType: "oic.d.thermostat") {
    capability "Temperature Measurement"
    capability "Relative Humidity Measurement"
    capability "Sensor"
    
    command "setTemperature" , ["number"]
    command "setHumidity", ["number"]
  }


  tiles {
    multiAttributeTile(name:"main", type:"thermostat", width:6, height:4) {
        tileAttribute("device.temperature", key: "PRIMARY_CONTROL") {
            attributeState "temperature", label:'${currentValue}°F', unit: "°F", backgroundColors: [
                    // Celsius Color Range
                    [value:  0, color: "#153591"],
                    [value:  7, color: "#1E9CBB"],
                    [value: 15, color: "#90D2A7"],
                    [value: 23, color: "#44B621"],
                    [value: 29, color: "#F1D801"],
                    [value: 33, color: "#D04E00"],
                    [value: 36, color: "#BC2323"],
                    // Fahrenheit Color Range
                    [value: 40, color: "#153591"],
                    [value: 44, color: "#1E9CBB"],
                    [value: 59, color: "#90D2A7"],
                    [value: 74, color: "#44B621"],
                    [value: 84, color: "#F1D801"],
                    [value: 92, color: "#D04E00"],
                    [value: 96, color: "#BC2323"]
                ]
        }
        tileAttribute("device.humidity", key: "SECONDARY_CONTROL") {
            attributeState("humidity", label:'${currentValue}%', unit:"%", defaultState: true)
        }
    }
    main "main"
    details "main"
  }
}



def setTemperature(val) {
    log.debug "Setting temperature for ${device.displayName} from external input, temperature = ${val}."
	sendEvent(name: "temperature", value: val, unit: location.getTemperatureScale())
}

def setHumidity(val) {
    log.debug "Setting humidity for ${device.displayName} from external input, humidity = ${val}."
    sendEvent(name: "humidity", value: val, unit: '%')
}
