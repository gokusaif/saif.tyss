import { Injectable } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class NavigationService {
  private history = [];

  constructor(
    private router: Router
  ) { }

  public loadRouting(): void {
    //   this.router.events
    //     .pipe(filter(event => event instanceof NavigationEnd))
    //     .subscribe(({urlAfterRedirects}: NavigationEnd) => {
    //       this.history = [...this.history, urlAfterRedirects];
    //     });
    // }
  }
}
