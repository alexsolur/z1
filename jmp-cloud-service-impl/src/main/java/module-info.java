import com.epam.service.Service;
import com.epam.service.cloud.CloudService;

module jmp.cloud.service.impl {
    requires transitive jmp.service.api;
    requires jmp.dto;
    exports com.epam.service.cloud;
    provides Service with CloudService;
}