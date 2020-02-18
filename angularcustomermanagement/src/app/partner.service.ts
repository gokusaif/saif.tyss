import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PartnerService {

  constructor(private http:HttpClient) { }

  apiUrl = 'http://localhost:8080';

  getPartners() : Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/partner/partners`);
  }


  getPartner(id) : Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/partner/${id}`);
  }
  
  addPartner(partner) :Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/partner/add-partner`,partner); 
  }

  
  deletePartner(id :number) :Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/partner/delete/${id}`);
  }

  updatePartner(partner) :Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/partner/update`,partner);
  }

}
