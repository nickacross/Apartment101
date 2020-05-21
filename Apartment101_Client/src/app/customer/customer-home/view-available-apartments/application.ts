import { Apartment } from 'src/app/shared/models/apartment';
import { User } from 'src/app/shared/models/user';

export interface Application{
    status: number;
    apartment: Apartment;
    user: User;
}