import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http:HttpClient) { }

  apiUrl = 'http://localhost:8080';


  getCustomers() : Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/customer/customers`);
  }

  addCustomer(customer) :Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/customer/add-customer`,customer); 
  }

  deleteCustomer(id :number) :Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/customer/delete/${id}`);
  }

  updateCustomer(customer) :Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/customer/update`,customer);
  }

  getCustomer(id :number) : Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/customer/${id}`);
  }
}
