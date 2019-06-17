import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {CategoryService} from '../../../services/category.service';
import {MatDialog} from '@angular/material';
import {IAlertDialogData} from '../../../interfaces/IAlertDialogData';
import {DialogType} from '../../../enums/DialogType';
import {AlertDialogComponent} from '../../../alert-dialog/alert-dialog.component';
import {UnitService} from '../../../services/unit.service';

@Component({
    selector: 'app-unit-create',
    templateUrl: './unit-create.component.html',
    styleUrls: ['./unit-create.component.css']
})
export class UnitCreateComponent implements OnInit {

    formUnit: FormGroup;

    constructor(private router: Router,
                private formBuilder: FormBuilder,
                private unitService: UnitService,
                private matDialog: MatDialog) {
    }

    ngOnInit() {
        this.formUnit = this.formBuilder.group({
            name: ['', Validators.required],
            note: [''],
        });
    }

    onSubmit(): void {
        this.unitService.create(this.formUnit.value)
            .subscribe(response => {
                if (response.code === 500) {
                    const alertData: IAlertDialogData = {content: response.message, type: DialogType.ERROR};
                    const dialogRef = this.matDialog.open(AlertDialogComponent, {
                        data: alertData
                    });
                } else if (response.code === 200) {
                    this.formUnit.reset({
                        name: '',
                        note: ''
                    });
                    const alertData: IAlertDialogData = {content: 'Tạo đơn vị tính thành công', type: DialogType.SUCESS};
                    const dialogRef = this.matDialog.open(AlertDialogComponent, {
                        data: alertData
                    });
                }
            }, error => {
                console.log(error);
            });
    }

}
