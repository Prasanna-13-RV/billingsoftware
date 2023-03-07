import { FooterService } from './services/footer/footer.service';
import { Component } from '@angular/core';
import { NavService } from './services/nav/nav.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'innobix';

  constructor(public nav: NavService, public footer: FooterService) { }

}
