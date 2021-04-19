package bside.palmtree.domain.coach;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach, Long> {

	List<Coach> findAll();
}
