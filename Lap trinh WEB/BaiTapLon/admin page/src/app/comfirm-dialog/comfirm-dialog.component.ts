import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {IAlertDialogData} from '../interfaces/IAlertDialogData';

@Component({
    selector: 'app-comfirm-dialog',
    templateUrl: './comfirm-dialog.component.html',
    styleUrls: ['./comfirm-dialog.component.css']
})
export class ComfirmDialogComponent implements OnInit {

    constructor(
        private dialogRef: MatDialogRef<ComfirmDialogComponent>,
        @Inject(MAT_DIALOG_DATA) public data: IAlertDialogData) {
    }

    onAgree() {
        this.dialogRef.close(true);
    }

    ngOnInit() {
    }

}
