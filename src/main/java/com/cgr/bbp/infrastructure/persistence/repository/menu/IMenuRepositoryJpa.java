package com.cgr.bbp.infrastructure.persistence.repository.menu;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cgr.bbp.infrastructure.persistence.entity.Menu.Menu;

public interface IMenuRepositoryJpa extends JpaRepository<Menu,Long> {


}
