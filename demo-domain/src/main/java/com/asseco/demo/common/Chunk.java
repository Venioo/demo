package com.asseco.demo.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Chunk<T> {

    private final List<T> list;

    private final boolean last;
}
