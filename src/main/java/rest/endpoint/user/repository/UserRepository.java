package rest.endpoint.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rest.endpoint.user.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {



}