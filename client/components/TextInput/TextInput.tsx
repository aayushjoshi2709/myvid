import React, { Dispatch, SetStateAction } from "react";

type InputValue = string | number | boolean;
interface FromInputProps {
  title: string;
  inputType: string;
  id: string;
  placeHolder?: string;
  required?: boolean;
  value?: string;
  onChange: Dispatch<SetStateAction<InputValue>>;
}

const TextInput = (props: FromInputProps): React.ReactElement => {
  return (
    <div className="mb-5">
      <label htmlFor={props.id} className="font-medium text-heading">
        {props.title}
      </label>
      <input
        onChange={(e) => {
          props.onChange(e.target.value);
        }}
        type={props.inputType}
        id={props.id}
        className="border rounded-xl border-[#bbbbbb] text-sm rounded-base w-full px-3 py-2.5 shadow-xs"
        placeholder={props.placeHolder}
        required={props.required}
        value={props.value}
      />
    </div>
  );
};

export default TextInput;
