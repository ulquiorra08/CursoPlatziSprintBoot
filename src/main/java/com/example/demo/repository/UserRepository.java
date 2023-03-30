package com.example.demo.repository;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.email=?1")
    Optional<User> findByUserEmail(String email);

    @Query("select u from User u where u.name like ?1%")
    List<User> findByAndSort(String name, Sort sort);

    List<User> findByName(String name);

    Optional<User> findByEmailAndName(String email, String name);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name, String email);

    List<User> findByBirthDateBetween(LocalDate begin,LocalDate end);

    List<User> findByNameContainingOrderByIdDesc(String name);

    @Query("Select new com.example.demo.dto.UserDto(u.id,u.name,u.birthDate)" +
            " from User u " +
            " where u.birthDate=:parametroFecha" +
            " and u.email=:parametroEmail")
    Optional<UserDto> getAllByBirtDateAndEmail(@Param("parametroFecha") LocalDate date, @Param("parametroEmail") String email);

}
