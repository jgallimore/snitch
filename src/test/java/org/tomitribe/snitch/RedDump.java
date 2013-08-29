package org.tomitribe.snitch;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

public class RedDump implements Opcodes {

    public static void main(String[] args) throws Exception {
        final byte[] dump = dump();
        final Class clazz = Bytecode.defineClass(dump, "org.tomitribe.snitch.Red", new URLClassLoader(new URL[0]));
        final Object blue = clazz.newInstance();

        Tracker.start();

        for (java.lang.reflect.Method method : clazz.getDeclaredMethods()) {
            try {
                final Class<?>[] types = method.getParameterTypes();
                final Object[] objects = new Object[types.length];
                for (int i = 0; i < types.length; i++) {
                    final Class<?> type = types[i];
                    if (!type.isPrimitive()) continue;

                    if (Byte.TYPE.equals(type)) {
                        objects[i] = (byte) 1;
                    } else if (Short.TYPE.equals(type)) {
                        objects[i] = (short) 1;
                    } else if (Integer.TYPE.equals(type)) {
                        objects[i] = 1;
                    } else if (Long.TYPE.equals(type)) {
                        objects[i] = 1l;
                    } else if (Float.TYPE.equals(type)) {
                        objects[i] = 1f;
                    } else if (Double.TYPE.equals(type)) {
                        objects[i] = 1d;
                    } else if (Character.TYPE.equals(type)) {
                        objects[i] = '1';
                    } else if (Boolean.TYPE.equals(type)) {
                        objects[i] = true;
                    } else {
                        throw new IllegalStateException("Unknown primitive type " + type.getName());
                    }

                }
                method.invoke(blue, objects);
            } catch (InvocationTargetException e) {
            }
        }

        Tracker.stop();
    }

    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, "org/tomitribe/snitch/Red", null, "java/lang/Object", null);

        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime0", "()V", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 1);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$voidMethodTime0", "()V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Red", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 3);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(3, 4);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime0", "()V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "byteMethodTime0", "()[B", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 1);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$byteMethodTime0", "()[B");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Red", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 4);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 4);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(3, 5);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$byteMethodTime0", "()[B", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "byteArrayMethodTime0", "()[[B", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 1);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$byteArrayMethodTime0", "()[[B");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Red", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 4);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 4);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(3, 5);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$byteArrayMethodTime0", "()[[B", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime1", "([B)V", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 2);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$voidMethodTime1", "([B)V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 2);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 3, new Object[]{"org/tomitribe/snitch/Red", "[B", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 4);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 2);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 4);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(3, 5);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime1", "([B)V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "booleanMethodTime1", "([B)[Z", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 2);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$booleanMethodTime1", "([B)[Z");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 2);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 4);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 3, new Object[]{"org/tomitribe/snitch/Red", "[B", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 5);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 2);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 5);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(3, 6);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$booleanMethodTime1", "([B)[Z", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "booleanArrayMethodTime1", "([B)[[Z", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 2);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$booleanArrayMethodTime1", "([B)[[Z");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 2);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 4);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 3, new Object[]{"org/tomitribe/snitch/Red", "[B", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 5);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 2);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 5);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(3, 6);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$booleanArrayMethodTime1", "([B)[[Z", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime2", "([B[Z)V", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 3);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$voidMethodTime2", "([B[Z)V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 3);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 4, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 5);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 3);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 5);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(3, 6);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime2", "([B[Z)V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "charMethodTime2", "([B[Z)[C", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 3);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$charMethodTime2", "([B[Z)[C");
            mv.visitVarInsn(ASTORE, 5);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 3);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 5);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 4, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 6);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 3);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 6);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(3, 7);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$charMethodTime2", "([B[Z)[C", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "charArrayMethodTime2", "([B[Z)[[C", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 3);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$charArrayMethodTime2", "([B[Z)[[C");
            mv.visitVarInsn(ASTORE, 5);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 3);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 5);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 4, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 6);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 3);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 6);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(3, 7);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$charArrayMethodTime2", "([B[Z)[[C", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime3", "([B[Z[C)V", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 4);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$voidMethodTime3", "([B[Z[C)V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 4);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 5, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", "[C", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 6);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 4);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 6);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(4, 7);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime3", "([B[Z[C)V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 4);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "shortMethodTime3", "([B[Z[C)[S", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 4);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$shortMethodTime3", "([B[Z[C)[S");
            mv.visitVarInsn(ASTORE, 6);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 4);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 6);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 5, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", "[C", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 7);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 4);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 7);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(4, 8);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$shortMethodTime3", "([B[Z[C)[S", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 4);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "shortArrayMethodTime3", "([B[Z[C)[[S", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 4);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$shortArrayMethodTime3", "([B[Z[C)[[S");
            mv.visitVarInsn(ASTORE, 6);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 4);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 6);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 5, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", "[C", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 7);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 4);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 7);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(4, 8);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$shortArrayMethodTime3", "([B[Z[C)[[S", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 4);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime4", "([B[Z[C[S)V", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 5);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$voidMethodTime4", "([B[Z[C[S)V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 5);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 6, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", "[C", "[S", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 7);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 5);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 7);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(5, 8);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime4", "([B[Z[C[S)V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 5);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "intMethodTime4", "([B[Z[C[S)[I", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 5);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$intMethodTime4", "([B[Z[C[S)[I");
            mv.visitVarInsn(ASTORE, 7);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 5);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 7);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 6, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", "[C", "[S", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 8);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 5);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 8);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(5, 9);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$intMethodTime4", "([B[Z[C[S)[I", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 5);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "intArrayMethodTime4", "([B[Z[C[S)[[I", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 5);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$intArrayMethodTime4", "([B[Z[C[S)[[I");
            mv.visitVarInsn(ASTORE, 7);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 5);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 7);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 6, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", "[C", "[S", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 8);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 5);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 8);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(5, 9);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$intArrayMethodTime4", "([B[Z[C[S)[[I", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 5);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime5", "([B[Z[C[S[I)V", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 6);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$voidMethodTime5", "([B[Z[C[S[I)V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 6);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 7, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", "[C", "[S", "[I", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 8);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 6);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 8);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(6, 9);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime5", "([B[Z[C[S[I)V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 6);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "longMethodTime5", "([B[Z[C[S[I)[J", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 6);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$longMethodTime5", "([B[Z[C[S[I)[J");
            mv.visitVarInsn(ASTORE, 8);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 6);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 8);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 7, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", "[C", "[S", "[I", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 9);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 6);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 9);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(6, 10);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$longMethodTime5", "([B[Z[C[S[I)[J", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 6);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "longArrayMethodTime5", "([B[Z[C[S[I)[[J", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 6);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$longArrayMethodTime5", "([B[Z[C[S[I)[[J");
            mv.visitVarInsn(ASTORE, 8);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 6);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 8);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 7, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", "[C", "[S", "[I", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 9);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 6);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 9);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(6, 10);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$longArrayMethodTime5", "([B[Z[C[S[I)[[J", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 6);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime6", "([B[Z[C[S[I[J)V", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 7);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitVarInsn(ALOAD, 6);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$voidMethodTime6", "([B[Z[C[S[I[J)V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 7);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 8, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", "[C", "[S", "[I", "[J", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 9);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 7);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 9);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(7, 10);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime6", "([B[Z[C[S[I[J)V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 7);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "floatMethodTime6", "([B[Z[C[S[I[J)[F", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 7);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitVarInsn(ALOAD, 6);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$floatMethodTime6", "([B[Z[C[S[I[J)[F");
            mv.visitVarInsn(ASTORE, 9);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 7);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 9);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 8, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", "[C", "[S", "[I", "[J", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 10);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 7);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 10);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(7, 11);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$floatMethodTime6", "([B[Z[C[S[I[J)[F", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 7);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "floatArrayMethodTime6", "([B[Z[C[S[I[J)[[F", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 7);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitVarInsn(ALOAD, 6);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$floatArrayMethodTime6", "([B[Z[C[S[I[J)[[F");
            mv.visitVarInsn(ASTORE, 9);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 7);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 9);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 8, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", "[C", "[S", "[I", "[J", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 10);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 7);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 10);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(7, 11);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$floatArrayMethodTime6", "([B[Z[C[S[I[J)[[F", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 7);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime7", "([B[Z[C[S[I[J[F)V", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 8);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitVarInsn(ALOAD, 6);
            mv.visitVarInsn(ALOAD, 7);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$voidMethodTime7", "([B[Z[C[S[I[J[F)V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 8);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 9, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", "[C", "[S", "[I", "[J", "[F", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 10);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 8);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 10);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(8, 11);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime7", "([B[Z[C[S[I[J[F)V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 8);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "doubleMethodTime7", "([B[Z[C[S[I[J[F)[D", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 8);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitVarInsn(ALOAD, 6);
            mv.visitVarInsn(ALOAD, 7);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$doubleMethodTime7", "([B[Z[C[S[I[J[F)[D");
            mv.visitVarInsn(ASTORE, 10);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 8);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 10);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 9, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", "[C", "[S", "[I", "[J", "[F", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 11);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 8);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 11);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(8, 12);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$doubleMethodTime7", "([B[Z[C[S[I[J[F)[D", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 8);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "doubleArrayMethodTime7", "([B[Z[C[S[I[J[F)[[D", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 8);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitVarInsn(ALOAD, 6);
            mv.visitVarInsn(ALOAD, 7);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$doubleArrayMethodTime7", "([B[Z[C[S[I[J[F)[[D");
            mv.visitVarInsn(ASTORE, 10);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 8);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 10);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 9, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", "[C", "[S", "[I", "[J", "[F", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 11);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 8);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 11);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(8, 12);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$doubleArrayMethodTime7", "([B[Z[C[S[I[J[F)[[D", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 8);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime8", "([B[Z[C[S[I[J[F[D)V", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 9);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitVarInsn(ALOAD, 6);
            mv.visitVarInsn(ALOAD, 7);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$voidMethodTime8", "([B[Z[C[S[I[J[F[D)V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 9);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 10, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", "[C", "[S", "[I", "[J", "[F", "[D", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 11);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 9);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 11);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(9, 12);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime8", "([B[Z[C[S[I[J[F[D)V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 9);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "DateMethodTime8", "([B[Z[C[S[I[J[F[D)[Ljava/util/Date;", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 9);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitVarInsn(ALOAD, 6);
            mv.visitVarInsn(ALOAD, 7);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$DateMethodTime8", "([B[Z[C[S[I[J[F[D)[Ljava/util/Date;");
            mv.visitVarInsn(ASTORE, 11);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 9);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 11);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 10, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", "[C", "[S", "[I", "[J", "[F", "[D", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 12);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 9);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 12);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(9, 13);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$DateMethodTime8", "([B[Z[C[S[I[J[F[D)[Ljava/util/Date;", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 9);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "DateArrayMethodTime8", "([B[Z[C[S[I[J[F[D)[[Ljava/util/Date;", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 9);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitVarInsn(ALOAD, 6);
            mv.visitVarInsn(ALOAD, 7);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$DateArrayMethodTime8", "([B[Z[C[S[I[J[F[D)[[Ljava/util/Date;");
            mv.visitVarInsn(ASTORE, 11);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 9);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 11);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 10, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", "[C", "[S", "[I", "[J", "[F", "[D", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 12);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 9);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 12);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(9, 13);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$DateArrayMethodTime8", "([B[Z[C[S[I[J[F[D)[[Ljava/util/Date;", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 9);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime9", "([B[Z[C[S[I[J[F[D[Ljava/util/Date;)V", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 10);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitVarInsn(ALOAD, 6);
            mv.visitVarInsn(ALOAD, 7);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitVarInsn(ALOAD, 9);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$voidMethodTime9", "([B[Z[C[S[I[J[F[D[Ljava/util/Date;)V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 10);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 11, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", "[C", "[S", "[I", "[J", "[F", "[D", "[Ljava/util/Date;", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 12);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 10);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 12);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(10, 13);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime9", "([B[Z[C[S[I[J[F[D[Ljava/util/Date;)V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 10);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "URIMethodTime9", "([B[Z[C[S[I[J[F[D[Ljava/util/Date;)[Ljava/net/URI;", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 10);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitVarInsn(ALOAD, 6);
            mv.visitVarInsn(ALOAD, 7);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitVarInsn(ALOAD, 9);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$URIMethodTime9", "([B[Z[C[S[I[J[F[D[Ljava/util/Date;)[Ljava/net/URI;");
            mv.visitVarInsn(ASTORE, 12);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 10);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 12);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 11, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", "[C", "[S", "[I", "[J", "[F", "[D", "[Ljava/util/Date;", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 13);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 10);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 13);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(10, 14);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$URIMethodTime9", "([B[Z[C[S[I[J[F[D[Ljava/util/Date;)[Ljava/net/URI;", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 10);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "URIArrayMethodTime9", "([B[Z[C[S[I[J[F[D[Ljava/util/Date;)[[Ljava/net/URI;", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 10);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitVarInsn(ALOAD, 6);
            mv.visitVarInsn(ALOAD, 7);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitVarInsn(ALOAD, 9);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Red", "track$URIArrayMethodTime9", "([B[Z[C[S[I[J[F[D[Ljava/util/Date;)[[Ljava/net/URI;");
            mv.visitVarInsn(ASTORE, 12);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 10);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 12);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 11, new Object[]{"org/tomitribe/snitch/Red", "[B", "[Z", "[C", "[S", "[I", "[J", "[F", "[D", "[Ljava/util/Date;", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 13);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 10);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 13);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(10, 14);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$URIArrayMethodTime9", "([B[Z[C[S[I[J[F[D[Ljava/util/Date;)[[Ljava/net/URI;", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 10);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }
}
