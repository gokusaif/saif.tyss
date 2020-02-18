import { Component, OnInit } from '@angular/core';
import { Customer } from '../customer';
import { Router, ActivatedRoute } from '@angular/router';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-view-customer',
  templateUrl: './view-customer.component.html',
  styleUrls: ['./view-customer.component.css']
})
export class ViewCustomerComponent implements OnInit {

  customer;
  id;
  constructor(private customerService:CustomerService,private activatedRouter:ActivatedRoute,
    private router:Router) {
    activatedRouter.params.subscribe( data => {
      this.id = data.id;
    })
    this.view(this.id);
   }

  ngOnInit(): void {
  }

  view(id :number) {
    this.customerService.getCustomer(id).subscribe( response => {
      this.customer = response.customer;
      console.log(response);
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
