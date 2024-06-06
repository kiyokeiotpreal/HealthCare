package org.example.doantotnghiep.Service.iservice;

import org.example.doantotnghiep.Payload.Response.DoctorSearchResponse;
import org.example.doantotnghiep.Payload.Response.SearchResponse;

import java.util.List;

public interface ISearchService {
    SearchResponse search(String name) throws Exception;
}
