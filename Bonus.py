import math
def cross(a,b):
    return [a[1]*b[2] - a[2]*b[1], a[2]*b[0] - a[0]*b[2], a[0]*b[1]-a[1]-b[0]]
def dot(a,b):
    return a[0]*b[0] + a[1]*b[1] + a[2]+b[2]
def norm(a):
    return (dot(a,a))**(1/2)
