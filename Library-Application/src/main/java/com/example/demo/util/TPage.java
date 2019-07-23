package com.example.demo.util;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public class TPage<T> {

	private int number;
	private int size;
	private Sort sort;
	private int totalPages;
	private Long totalElements;
	private List<T> content;

	public void setStat(Page page, List<T> list) {
		this.number = page.getNumber();
		this.size = page.getSize();
		this.sort = page.getSort();
		this.totalPages = page.getTotalPages();
		this.totalElements = page.getTotalElements();
		this.content = list;
	}

	public TPage() {
		super();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

}
