//package com.lifegraph.team20.search;
//
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;
//
//import lombok.Data;
//
//@Data
//public class Search {
//
//	@NotBlank
//    @Size(max = 18)
//    private String id;
//
//    @NotBlank
//    private String value;
//
//    public Search toDomainUser() {
//        return Search.builder()
//                .id(this.id)
//                .value(this.value)
//                .build();
//	}
//}















//@SpringBootApplication
//public class Search {
//	public static void main(String[] args) {
//		SpringApplication.run(Search.class, args);
//	}
//}