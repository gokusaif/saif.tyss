import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PartnerService } from '../partner.service';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-view-partner',
  templateUrl: './view-partner.component.html',
  styleUrls: ['./view-partner.component.css']
})
export class ViewPartnerComponent implements OnInit {

  id;
  partner;

  constructor(private activatedRoute: ActivatedRoute, private partnerService: PartnerService, 
    private router: Router,private customerService:CustomerService) {
    activatedRoute.params.subscribe(data => {
      this.id = data.id;
    });
    this.viewPartner(this.id);
  }

  ngOnInit(): void {
  }

  update(partner) {
    this.router.navigate([`/update-partner/${partner.id}`],
      { queryParams: { partnerName: partner.name, partnerStatus: partner.status, partnerAddress: partner.addresses, partnerEmail: partner.emails, partnerPhoneNumber: partner.phoneNumbers } })
  }

  viewPartner(id) {
    this.partnerService.getPartner(id).subscribe(response => {
      console.log(response);
      this.partner = response.partner;
      if( this.partner.partnerId === null) {
        this.partner.partnerId = 'Owner';
      }
      console.log(this.partner);

    });
  }

  updateCustomer(customer) {
    this.router.navigate([`update-customer/${customer.id}`],
      { queryParams: { customerName: customer.name, customerStatus:customer.status, customerAddress: customer.addresses, customerEmail: customer.emails, customerPhoneNumber: customer.phoneNumbers } }
    );
  }

  deleteCustomer(id: number) {
    console.log(id);
    this.customerService.deleteCustomer(id).subscribe(response => {
      console.log(response);
      if (response.statusCode === 201) {
        window.location.reload();
      }
    })
  }

  addCustomer() {
    this.router.navigate([`/add-customer/${this.id}`]);
  }

}
