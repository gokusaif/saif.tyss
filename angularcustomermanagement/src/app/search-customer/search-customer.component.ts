import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-customer',
  templateUrl: './search-customer.component.html',
  styleUrls: ['./search-customer.component.css']
})
export class SearchCustomerComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  view(id) {
    this.router.navigate([`/view-customer/${id}`]);
  }

}
