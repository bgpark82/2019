import React from 'react';
import { AuthTemplate } from '../components/auth/AuthTemplate';
import { AuthForm } from '../components/auth/AuthForm';

export default function RegisterPage() {
  return (
    <AuthTemplate>
      <AuthForm type="register" />
    </AuthTemplate>
  );
}
