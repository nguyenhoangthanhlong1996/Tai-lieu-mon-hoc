import {AfterViewInit, Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material';
import {IAlertDialogData} from '../../../interfaces/IAlertDialogData';
import {DialogType} from '../../../enums/DialogType';
import {AlertDialogComponent} from '../../../alert-dialog/alert-dialog.component';
import {CompanyInfoService} from '../../../services/company-info.service';
import {ICompanyInfo} from '../../../interfaces/company-info/ICompanyInfo';

@Component({
    selector: 'app-company-info-change',
    templateUrl: './company-info-change.component.html',
    styleUrls: ['./company-info-change.component.css']
})
export class CompanyInfoChangeComponent implements OnInit {

    formCompanyInfoChange: FormGroup;

    constructor(private router: Router,
                private formBuilder: FormBuilder,
                private companyInfoService: CompanyInfoService,
                private matDialog: MatDialog) {
    }

    ngOnInit() {
        // Thiếp lập form
        this.formCompanyInfoChange = this.formBuilder.group({
            name: ['', Validators.required],
            aboutContent: ['', Validators.required],
            address: ['', Validators.required],
            email: ['', Validators.required],
            website: ['', Validators.required],
            hotline: ['', Validators.required]
        });
        // Lấy thông tin công ty
        this.companyInfoService.get()
            .subscribe(response => {
                if (response.code === 200) {
                    const companyInfo: ICompanyInfo = response.data;
                    // Gán các giá trị lên form
                    this.formCompanyInfoChange.get('name').setValue(companyInfo.name);
                    this.formCompanyInfoChange.get('aboutContent').setValue(companyInfo.about_content);
                    this.formCompanyInfoChange.get('address').setValue(companyInfo.address);
                    this.formCompanyInfoChange.get('email').setValue(companyInfo.email);
                    this.formCompanyInfoChange.get('website').setValue(companyInfo.website);
                    this.formCompanyInfoChange.get('hotline').setValue(companyInfo.hotline);
                } else if (response.code === 404) {
                    const alertData: IAlertDialogData = {content: 'Không có thông tin', type: DialogType.SUCESS};
                    const dialogRef = this.matDialog.open(AlertDialogComponent, {
                        data: alertData
                    });
                }
            }, error => {
                console.log(error);
            });
    }

    onSubmit(): void {
        this.companyInfoService.change(this.formCompanyInfoChange.value)
            .subscribe(response => {
                if (response.code === 200) {
                    const alertData: IAlertDialogData = {content: 'Sửa thông tin công ty thành công', type: DialogType.SUCESS};
                    const dialogRef = this.matDialog.open(AlertDialogComponent, {
                        data: alertData
                    });
                } else {
                    const alertData: IAlertDialogData = {content: response.message, type: DialogType.ERROR};
                    const dialogRef = this.matDialog.open(AlertDialogComponent, {
                        data: alertData
                    });
                }
            }, error => {
                console.log(error);
            });
    }

}
