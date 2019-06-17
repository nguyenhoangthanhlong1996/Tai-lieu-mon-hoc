import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {IUser} from '../../../interfaces/user/IUser';

@Component({
  selector: 'app-user-info-dialog',
  templateUrl: './user-info-dialog.component.html',
  styleUrls: ['./user-info-dialog.component.css']
})
export class UserInfoDialogComponent implements OnInit {

  constructor(
      private dialogRef: MatDialogRef<UserInfoDialogComponent>,
      @Inject(MAT_DIALOG_DATA) public data: IUser) {
  }

  ngOnInit() {
  }

}
