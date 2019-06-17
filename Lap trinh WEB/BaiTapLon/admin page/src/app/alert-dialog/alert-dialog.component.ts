import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {IAlertDialogData} from '../interfaces/IAlertDialogData';

@Component({
    selector: 'app-alert-dialog',
    templateUrl: './alert-dialog.component.html',
    styleUrls: ['./alert-dialog.component.css']
})
export class AlertDialogComponent implements OnInit {
    constructor(
        private dialogRef: MatDialogRef<AlertDialogComponent>,
        @Inject(MAT_DIALOG_DATA) public data: IAlertDialogData) {
    }

    // onNoClick(): void {
    //     this.dialogRef.close();
    // }

    ngOnInit() {
    }
}
