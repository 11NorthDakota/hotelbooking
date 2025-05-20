package by.northdakota.booking_backend.Service;

import by.northdakota.booking_backend.Entity.Role;
import by.northdakota.booking_backend.Repository.RoleRepository;
import by.northdakota.booking_backend.Service.Interface.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Transactional
    @Override
    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }

    @Transactional
    @Override
    public Role getAdminRole() {
        return roleRepository.findByName("ROLE_ADMIN").get();
    }
}
