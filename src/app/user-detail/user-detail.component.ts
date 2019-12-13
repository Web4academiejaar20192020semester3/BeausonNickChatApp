import { Component, OnInit, Input } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {

  @Input() user: User;  // Krijgt user mee via html tag in users.component.ts

  constructor(private userService: UserService) { }

  save(): void {
    this.userService.updateUser(this.user)
      .subscribe(res => {});
  }


  ngOnInit() {
  }

}
