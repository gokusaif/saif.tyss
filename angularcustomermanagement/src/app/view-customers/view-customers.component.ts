import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../customer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-customers',
  templateUrl: './view-customers.component.html',
  styleUrls: ['./view-customers.component.css']
})
export class ViewCustomersComponent implements OnInit {

  customers;

  constructor(private customerService: CustomerService, private router: Router) {
    this.viewCustomers();
  }

  ngOnInit(): void {
  }

  viewCustomers() {
    this.customerService.getCustomers().subscribe(response => {
      console.log(response);
      this.customers = response.customers;
    });
  }

  delete(id: number) {
    console.log(id);
    this.customerService.deleteCustomer(id).subscribe(response => {
      console.log(response);
      if (response.statusCode === 201) {
        window.location.reload();
      }
    })
  }

  update(customer) {
    this.router.navigate([`update-customer/${customer.id}`],
      { queryParams: { customerName: customer.name, customerStatus:customer.status, customerAddress: customer.addresses, customerEmail: customer.emails, customerPhoneNumber: customer.phoneNumbers } }
    );
  }

}
