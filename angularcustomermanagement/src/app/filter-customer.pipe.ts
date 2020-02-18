import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterCustomer'
})
export class FilterCustomerPipe implements PipeTransform {

  transform(customers: any[],search: String) {
    if( search === undefined){
      return customers;
    }
    return customers.filter((customer, index) => {
      return customer.name.toLowerCase().includes(search.toLowerCase());
    });
  }

}
