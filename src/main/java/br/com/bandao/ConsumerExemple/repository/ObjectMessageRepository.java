package br.com.bandao.ConsumerExemple.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bandao.ConsumerExemple.model.ObjectMessage;


public interface ObjectMessageRepository extends JpaRepository<ObjectMessage, Long> {
}
