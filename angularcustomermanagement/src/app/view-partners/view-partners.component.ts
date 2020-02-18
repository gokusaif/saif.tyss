import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PartnerService } from '../partner.service';
import { Router } from '@angular/router';
import { Partner } from '../partner';

@Component({
  selector: 'app-view-partners',
  templateUrl: './view-partners.component.html',
  styleUrls: ['./view-partners.component.css']
})
export class ViewPartnersComponent implements OnInit {

  partners;

  constructor(private partnerService: PartnerService, private router: Router) {
    this.viewPartners();
  }

  ngOnInit(): void {
  }

  viewPartners() {
    this.partnerService.getPartners().subscribe(response => {
      console.log(response);
      this.partners = response.partners;
      console.log(this.partners);

    });
  }

  view(id) {
    this.router.navigate([`/view-partner/${id}`]);
  }

  delete(id: number) {
    console.log(id);
    this.partnerService.deletePartner(id).subscribe(response => {
      console.log(response);
      if (response.statusCode === 201) {
        window.location.reload();
      }
    })
  }

  update(partner) {
    this.router.navigate([`/update-partner/${partner.id}`],
      { queryParams: { partnerName: partner.name, partnerStatus: partner.status, partnerAddress: partner.addresses, partnerEmail: partner.emails, partnerPhoneNumber: partner.phoneNumbers } })
  }


}
