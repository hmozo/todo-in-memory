package com.apress.todo.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.apress.todo.domain.ToDo;

@Repository ("in-memory")
public class ToDoRepository_inmemory implements CommonRepository<ToDo>{

	private Map<String, ToDo> toDos= new HashMap<>();

	@Override
	public ToDo save(ToDo domain) {
		ToDo result= toDos.get(domain.getId());
		if(result!=null) {
			result.setModified(LocalDateTime.now());
			result.setDescription(domain.getDescription());
			result.setCompleted(domain.isCompleted());
			domain= result;
		}
		toDos.put(domain.getId(), domain);
		return toDos.get(domain.getId());
	}

	@Override
	public Iterable<ToDo> save(Collection<ToDo> domains) {
		domains.forEach(this::save);
		return this.findAll();
	}

	@Override
	public void delete(ToDo domain) {
		toDos.remove(domain.getId());
	}

	@Override
	public ToDo findById(String id) {
		return toDos.get(id);
	}

	@Override
	public Iterable<ToDo> findAll() {
		Comparator<ToDo> byToDoName= (a,b)->
			a.getDescription().compareTo(b.getDescription());
		return this.toDos.entrySet().stream()
			.map(Map.Entry<String,ToDo>::getValue)
			.sorted(byToDoName).collect(Collectors.toList());
	}

	
}
