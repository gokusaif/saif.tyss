import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule,FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { ViewPartnersComponent } from './view-partners/view-partners.component';
import { ViewCustomersComponent } from './view-customers/view-customers.component';
import { AddCustomerComponent } from './add-customer/add-customer.component';
import { AddPartnerComponent } from './add-partner/add-partner.component';
import { ViewPartnerComponent } from './view-partner/view-partner.component';
import { ViewCustomerComponent } from './view-customer/view-customer.component';
import { UpdateCustomerComponent } from './update-customer/update-customer.component';
import { UpdatePartnerComponent } from './update-partner/update-partner.component';
import { SearchPartnerComponent } from './search-partner/search-partner.component';
import { SearchCustomerComponent } from './search-customer/search-customer.component';
import { FilterPartnerPipe } from './filter-partner.pipe';
import { FilterCustomerPipe } from './filter-customer.pipe';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavBarComponent,
    PagenotfoundComponent,
    ViewPartnersComponent,
    ViewCustomersComponent,
    AddCustomerComponent,
    AddPartnerComponent,
    ViewPartnerComponent,
    ViewCustomerComponent,
    UpdateCustomerComponent,
    UpdatePartnerComponent,
    SearchPartnerComponent,
    SearchCustomerComponent,
    FilterPartnerPipe,
    FilterCustomerPipe
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot([
      { path:'', component:HomeComponent },
      { path:'add-partner',component:AddPartnerComponent },
      { path:'update-partner/:id', component:UpdatePartnerComponent},
      { path:'view-partners',component:ViewPartnersComponent },
      { path:'view-partner/:id',component:ViewPartnerComponent },
      { path:'search-partner',component:SearchPartnerComponent},
      { path:'add-customer/:id',component:AddCustomerComponent },
      { path:'update-customer/:id',component:UpdateCustomerComponent },
      { path:'search-customer',component:SearchCustomerComponent},
      { path:'view-customer/:id',component: ViewCustomerComponent },
      { path:'view-customers',component:ViewCustomersComponent },
      { path:"**", component:PagenotfoundComponent }
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
