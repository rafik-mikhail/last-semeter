from math import *


def factorizeN(n):
    p, q = 0, 0
    p, q = find_p_and_q(sieve_of_eratosthenes(n), n)
    return p, q


def findD(n, e):
    d = 0
    p, q = factorizeN(n)
    totient = find_totient(p, q)
    gcd, x, y = egcd(e, totient)
    return x


def decrypt(n, d, c):
    m = 0
    m = (c**d) % n
    return m


def sieve_of_eratosthenes(max_integer):
    sieve = [True for _ in range(max_integer + 1)]
    sieve[0:1] = [False, False]
    for start in range(2, max_integer + 1):
        if sieve[start]:
            for i in range(2 * start, max_integer + 1, start):
                sieve[i] = False
    primes = []
    for i in range(2, max_integer + 1):
        if sieve[i]:
            primes.append(i)
    return primes


def egcd(a, b):
    if a == 0:
        return (b, 0, 1)
    else:
        gcd, x, y = egcd(b % a, a)
        return (gcd, y - (b//a) * x, x)


def find_p_and_q(prime_numbers, n):
    for number in prime_numbers:
        for second_number in prime_numbers:
            if (number * second_number) % n == 0:
                return(number, second_number)

    return ('cant find them bruv')


def find_totient(p, q):
    return ((p-1) * (q-1))


if __name__ == "__main__":
    d = findD(143, 11)
    print("The PrivateKey d is: ", d)
    print("The decrypted message is: ", decrypt(143, d, 106))

    d1 = findD(91, 5)
    print("The PrivateKey d is: ", d1)
    print("The decrypted message is: ", decrypt(91, d1, 9))

    d2 = findD(391, 15)
    print("The PrivateKey d is: ", d2)
    print("The decrypted message is: ", decrypt(391, d2, 364))
