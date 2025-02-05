package com.galaxy.proyectofinal_client.dtos;

import java.util.List;

public class PageDTO<T> {
    private List<T> content;
    private int number;
    private int totalPages;
    private int size;

    // Getters y Setters
    public List<T> getContent() { return content; }
    public void setContent(List<T> content) { this.content = content; }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public int getTotalPages() { return totalPages; }
    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
}
