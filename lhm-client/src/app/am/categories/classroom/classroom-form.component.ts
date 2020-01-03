import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Classroom } from './classroom';
import { Device } from '../device/device';
import { Amphitheater } from '../amphitheater/amphitheater';
import { Constants } from '../../common/util/constants';

/**
 * @description: Define the country form. Use for enter data of the country object
 */
export class ClassroomForm {
    /**
     * @description Define the country form
     * @param fb
     */
    static classroomForm(fb: FormBuilder, business: string): FormGroup {
        var classroomForm: FormGroup;

        classroomForm = fb.group({
            // id: 0,
            idClassroom: "",

            amphitheater: fb.group({
                idAmphitheater: [1, Validators.compose([
                    Validators.required,
                    Validators.min(1)
                ])]
            }),
            device: fb.group({
                deviceId: [0, Validators.compose([
                    Validators.required,
                    Validators.min(1)
                ])]
            }),
            symbol: ["", Validators.compose([
                Validators.required,
                Validators.maxLength(50),
                Validators.pattern(Constants.NAME_PATTERN)
            ])],
            nameClassroom: ["", Validators.compose([
                Validators.required,
                Validators.maxLength(50),
                Validators.pattern(Constants.NAME_PATTERN)
            ])],
            amount: ["", Validators.compose([
                Validators.required,
                Validators.maxLength(50),
                Validators.pattern(Constants.NAME_PATTERN)
            ])],
            size: ["", Validators.compose([
                Validators.required,
                Validators.maxLength(50),
                Validators.pattern(Constants.NAME_PATTERN)
            ])],
            chucNang: ["", Validators.compose([
                Validators.required,
                Validators.maxLength(50),
                Validators.pattern(Constants.NAME_PATTERN)
            ])],
            creationTime: ["", Validators.compose([
                Validators.required,
                Validators.maxLength(50),
                Validators.pattern(Constants.NAME_PATTERN)
            ])],
            createdBy: ["", Validators.compose([
                Validators.required,
                Validators.maxLength(50),
                Validators.pattern(Constants.NAME_PATTERN)
            ])],
            updatedBy: ["", Validators.compose([
                Validators.required,
                Validators.maxLength(50),
                Validators.pattern(Constants.NAME_PATTERN)
            ])],
            statuss: [1, Validators.compose([
                Validators.required,
                Validators.maxLength(50),
                Validators.pattern(Constants.NAME_PATTERN)
            ])],
        });
        return classroomForm;
    }

    /**
     * @description : set the inital value for the form
     * @param countryForm : country form
     * @param country : Data used to set up for form
     */
    static bindingData(classroomForm: FormGroup, Classroom: Classroom) {
        debugger
        classroomForm.patchValue({
            idClassroom: Classroom.idClassroom,
            amphitheater: Classroom.amphitheater,
            // staffInCharge: Classroom.staffInCharge,
            symbol: Classroom.symbol,
            nameClassroom: Classroom.nameClassroom,
            amount: Classroom.amount,
            size: Classroom.size,
            chucNang: Classroom.chucNang,
            creationTime: Classroom.createTime,
            createdBy: Classroom.createdBy,
            updateTime: Classroom.updateTime,
            updatedBy: Classroom.updatedBy,
            statuss: Classroom.statuss,
        });

    }
}
