import { useAuth } from '../../contexts/AuthContext'
import { useNavigate } from 'react-router-dom'
import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'
import * as z from 'zod'
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Button } from '@/components/ui/button'
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormMessage,
} from '@/components/ui/form'

// validation schema
const loginSchema = z.object({
  usernameOrEmail: z.string()
    .min(1, 'Username or email is required')
    .min(3, 'Must be at least 3 characters'),
  password: z.string()
    .min(1, 'Password is required')
    .min(8, 'Password must be at least 8 characters'),
})

export default function Login() {
    const { login, loading, error, clearError } = useAuth();
    const navigate = useNavigate();

    const form = useForm({
        resolver: zodResolver(loginSchema),
        defaultValues: {
            usernameOrEmail: '',
            password: '',
        },
    })

    const onSubmit = async (values) => {
        clearError(); // clear any previous errors
        
        const loginData = {
            usernameOrEmail: values.usernameOrEmail,
            password: values.password
        };
        
        const result = await login(loginData);
        if (result.success) {
            navigate('/dashboard');
        }
    }

    return (
        <Card className='w-full max-w-s p-8'>
            <CardHeader>
                <CardTitle>Login to WorkFlow</CardTitle>
                <CardDescription>Your workplace, simplified.</CardDescription>
            </CardHeader>
            <CardContent className='p-8'>
                <Form {...form}>
                    <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-6">
                        {error && (
                            <div className="text-destructive text-sm font-medium">
                                {error}
                            </div>
                        )}
                        
                        <FormField
                            control={form.control}
                            name="usernameOrEmail"
                            render={({ field }) => (
                                <FormItem>
                                    <FormControl>
                                        <Input 
                                            placeholder="Enter username or email"
                                            {...field}
                                        />
                                    </FormControl>
                                    <FormMessage />
                                </FormItem>
                            )}
                        />
                        
                        <FormField
                            control={form.control}
                            name="password"
                            render={({ field }) => (
                                <FormItem>
                                    <FormControl>
                                        <Input 
                                            type="password"
                                            placeholder="Enter password"
                                            {...field}
                                        />
                                    </FormControl>
                                    <FormMessage />
                                </FormItem>
                            )}
                        />
                        
                        <div className="text-right">
                            <a href="#" className="text-sm text-primary hover:underline">
                                Forgot your password?
                            </a>
                        </div>
                        
                        <Button 
                            type="submit" 
                            className="w-full" 
                            disabled={loading}
                        >
                            {loading ? 'Logging in...' : 'Login'}
                        </Button>
                    </form>
                </Form>
            </CardContent>
        </Card>
    );
}