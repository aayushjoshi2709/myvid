import React from "react";

interface FromInputProps {
  title: string;
  inputType: string;
  id: string;
  placeHolder?: string;
  required?: boolean;
}

const FormInput = (props: FromInputProps): React.ReactElement => {
  return (
    <div className="mb-5">
      <label htmlFor={props.id} className="font-medium text-heading">
        {props.title}
      </label>
      <input
        type={props.inputType}
        id={props.id}
        className="border rounded-2xl border-[#bbbbbb] text-sm rounded-base w-full px-3 py-2.5 shadow-xs"
        placeholder={props.placeHolder}
        required={props.required}
      />
    </div>
  );
};

export default FormInput;
