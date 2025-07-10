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
const signinSchema = z.object({
  usernameOrEmail: z.string()
    .min(1, 'Username or email is required')
    .min(3, 'Must be at least 3 characters'),
  password: z.string()
    .min(1, 'Password is required')
    .min(8, 'Password must be at least 8 characters'),
})

export default function Signin() {
    const { signin, loading, error, clearError } = useAuth();
    const navigate = useNavigate();

    const form = useForm({
        resolver: zodResolver(signinSchema),
        defaultValues: {
            usernameOrEmail: '',
            password: '',
        },
    })

    const onSubmit = async (values) => {
        clearError(); // clear any previous errors
        
        const signinData = {
            usernameOrEmail: values.usernameOrEmail,
            password: values.password
        };
        
        const result = await signin(signinData);
        if (result.success) {
            navigate('/dashboard');
        }
    }

    return (
        <div className="min-h-screen flex flex-col items-center justify-center p-6 bg-background">
            <h1 className='text-3xl font-bold p-1'>Welcome to WorkFlow</h1>
            <h2 className='pb-6'>Your workplace, simplified</h2>
            <Card className="w-full max-w-md">
                <CardHeader>
                    <CardTitle>Sign in to your account</CardTitle>
                    <CardDescription>Enter your email or username below to sign in.</CardDescription>
                </CardHeader>
                <CardContent className="p-8">
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
                                {loading ? 'Signing in...' : 'Sign in'}
                            </Button>
                        </form>
                    </Form>
                </CardContent>
            </Card>
                <div className="text-muted-foreground text-xs p-6">
                    By signing in, you agree to our{" "}
                    <a href="#" className="text-primary hover:underline underline-offset-4">
                        Terms of Service
                    </a>{" "}
                    and{" "}
                    <a href="#" className="text-primary hover:underline underline-offset-4">
                        Privacy Policy
                    </a>
                </div>
        </div>
    );
}