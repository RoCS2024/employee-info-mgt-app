module com.employee.info.mgt.app.employeeinfomgtapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires umsv2;
    requires eims;

    requires org.apache.logging.log4j.slf4j2.impl;
            
                            
    opens com.employee.info.mgt.app to javafx.fxml;
    opens com.employee.info.mgt.app.controllers.item to javafx.fxml;
    opens com.employee.info.mgt.app.controllers.dashboard to javafx.fxml;
    opens com.employee.info.mgt.app.controllers.main to javafx.fxml;

    exports com.employee.info.mgt.app;
    exports com.employee.info.mgt.app.controllers.item;
    exports com.employee.info.mgt.app.controllers.dashboard to javafx.fxml;
    exports com.employee.info.mgt.app.controllers.main to javafx.fxml;
}