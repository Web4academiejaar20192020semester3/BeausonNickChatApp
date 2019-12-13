import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { USERS } from '../mock-users';
import { UserService} from '../user.service';
import {Observable, timer} from 'rxjs';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: User[];
  selectedUser: User;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(): void {
    timer(0, 2000)
      .subscribe( () => {
        this.userService.getUsers()
          .subscribe(users => this.users = users);
      });
  }

  onSelect(user: User): void {
    this.selectedUser = user;
  }
}



