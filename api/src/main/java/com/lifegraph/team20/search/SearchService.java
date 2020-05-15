//package com.lifegraph.team20.search;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//@Service
//public class SearchService {
//  @Autowired
//  @Qualifier("zipCodeSearchRestTemplate")
//  RestTemplate restTemplate;
//  /** 郵便番号検索API リクエストURL */
//  private static final String URL = "http://zipcloud.ibsnet.co.jp/api/search?zipcode={zipcode}";
//  public ZipCodeDto service(String zipcode) {
//    return restTemplate.getForObject(URL, ZipCodeDto.class, zipcode);
//  }
//}
//@Entity
//@Table(name = "parent_graphs")
//public class SearchS {
//}
////    @Id
////    @GEnerated
////}
//
//import org.springframework.stereotype.Service;
////
////@Service
////public interface SearchService
////
////  public List<SearchList>
////
////}