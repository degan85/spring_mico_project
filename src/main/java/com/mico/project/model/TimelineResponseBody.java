package com.mico.project.model;

import java.util.List;

import com.mico.project.domain.Timeline;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class TimelineResponseBody {
	String msg;
    List<Timeline> result;
}
