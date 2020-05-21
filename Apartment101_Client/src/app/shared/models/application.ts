import { User } from './user';

export class Application {
    appId: number;
    status: number; // 0 = false, 1 = true
    user: User;
}