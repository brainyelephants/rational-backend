package ru.brainyelephants.rational.model.dto;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageDTO<T> {

    private long totalElements;

    private int totalPages;

    private List<T> content;

    private int number;

    private int numberOfElements;

    private boolean hasContent;

    private boolean hasNext;

    private boolean hasPrevious;

    private boolean isEmpty;

    private boolean isFirst;

    private boolean isLast;

    public <S> PageDTO(Page<S> page, Class<T> tClass) {
        totalElements = page.getTotalElements();
        totalPages = page.getTotalPages();
        number = page.getNumber();
        numberOfElements = page.getNumberOfElements();
        hasContent = page.hasContent();
        hasNext = page.hasNext();
        hasPrevious = page.hasPrevious();
        isEmpty = page.isEmpty();
        isFirst = page.isFirst();
        isLast = page.isLast();
        ModelMapper modelMapper = new ModelMapper();
        content = new ArrayList<>();
        for (S entity : page.getContent()) {
            content.add(modelMapper.map(entity, tClass));
        }
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isHasContent() {
        return hasContent;
    }

    public void setHasContent(boolean hasContent) {
        this.hasContent = hasContent;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }
}
