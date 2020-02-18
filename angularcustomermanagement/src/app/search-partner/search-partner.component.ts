import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-partner',
  templateUrl: './search-partner.component.html',
  styleUrls: ['./search-partner.component.css']
})
export class SearchPartnerComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  view(id) {
    this.router.navigate([`/view-partner/${id}`]);
  }

}
