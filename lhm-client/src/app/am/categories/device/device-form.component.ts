import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Device } from './device';
import { Constants } from '../../common/util/constants';

/**
 * @description: Define the country form. Use for enter data of the country object
 */
export class DeviceForm {
    /**
     * @description Define the country form
     * @param fb
     */
    static DeviceForm(fb: FormBuilder, business: string): FormGroup {
        var DeviceForm: FormGroup;
        DeviceForm = fb.group({
            idDevice: "",

            classroom: fb.group({
                idClassroom: [0, Validators.compose([
                    Validators.required,
                    Validators.min(1),
                ])]

            }),
            nameDevice:["", Validators.compose([
                Validators.required,
                Validators.maxLength(50),
                Validators.pattern(Constants.NAME_PATTERN)
            ])],
            amount: ["", Validators.compose([
                Validators.required,
                Validators.maxLength(50),
                Validators.pattern(Constants.NAME_PATTERN)
            ])],
            createTime: ["", Validators.compose([
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
        return DeviceForm;
    }

    /**
     * @description : set the inital value for the form
     * @param deviceForm : country form
     * @param country : Data used to set up for form
     */
    static bindingData(deviceForm: FormGroup, Device: Device) {
        debugger
        deviceForm.patchValue({
            idDevice: Device.idDevice,
            classroom: Device.classroom,
            idClassroom: Device.classroom.idClassroom,
            nameDevice: Device.nameDeivce,
            amount: Device.amount,
            creationTime: Device.createTime,
            createdBy: Device.createdBy,
            updateTime: Device.updateTime,
            updatedBy: Device.updatedBy,
            statuss: Device.statuss,
        });

    }
}
