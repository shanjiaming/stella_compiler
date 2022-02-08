	.text
	.file	"builtin.cpp"

	.globl	string__add     # -- Begin function string__add
	.p2align	2
	.type	string__add,@function
string__add:                    # @string__add
	.cfi_startproc
# %bb.0:
    addi	sp, sp, -32
    .cfi_def_cfa_offset 32
    sw	ra, 28(sp)
    sw	s0, 24(sp)
    sw	s1, 20(sp)
    .cfi_offset ra, -4
    .cfi_offset s0, -8
    .cfi_offset s1, -12
    sw	a0, 16(sp)
    sw	a1, 8(sp)
    call	strlen
    lw	a2, 8(sp)
    mv	s0, a0
    mv	s1, a1
    mv	a0, a2
    call	strlen
    add	a1, s1, a1
    add	a2, s0, a0
    sltu	a0, a2, s0
    add	a1, a1, a0
    addi	a0, a2, 4
    sltu	a2, a0, a2
    add	a1, a1, a2
    call	malloc
    lw	a1, 16(sp)
    sw	a0, 0(sp)
    call	strcpy
    lw	a0, 0(sp)
    lw	a1, 8(sp)
    call	strcat
    lw	a0, 0(sp)
    lw	s1, 20(sp)
    lw	s0, 24(sp)
    lw	ra, 28(sp)
    addi	sp, sp, 32
    ret
.Lfunc_end1:
	.size	string__add, .Lfunc_end1-string__add
	.cfi_endproc
                                        # -- End function
	.globl	string__eq      # -- Begin function string__eq
	.p2align	2
	.type	string__eq,@function
string__eq:                     # @string__eq
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	sw	a1, 0(sp)
	call	strcmp
	seqz	a0, a0
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end2:
	.size	string__eq, .Lfunc_end2-string__eq
	.cfi_endproc
                                        # -- End function
	.globl	string__neq     # -- Begin function string__neq
	.p2align	2
	.type	string__neq,@function
string__neq:                    # @string__neq
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	sw	a1, 0(sp)
	call	strcmp
	snez	a0, a0
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end3:
	.size	string__neq, .Lfunc_end3-string__neq
	.cfi_endproc
                                        # -- End function
	.globl	string__l        # -- Begin function string__l
	.p2align	2
	.type	string__l,@function
string__l:                       # @string__l
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	sw	a1, 0(sp)
	call	strcmp
	srli	a0, a0, 31
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end4:
	.size	string__l, .Lfunc_end4-string__l
	.cfi_endproc
                                        # -- End function
	.globl	string__leq     # -- Begin function string__leq
	.p2align	2
	.type	string__leq,@function
string__leq:                    # @string__leq
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	sw	a1, 0(sp)
	call	strcmp
	slti	a0, a0, 1
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end5:
	.size	string__leq, .Lfunc_end5-string__leq
	.cfi_endproc
                                        # -- End function
	.globl	string__g        # -- Begin function string__g
	.p2align	2
	.type	string__g,@function
string__g:                       # @string__g
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	sw	a1, 0(sp)
	call	strcmp
	sgtz	a0, a0
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end6:
	.size	string__g, .Lfunc_end6-string__g
	.cfi_endproc
                                        # -- End function
	.globl	string__geq     # -- Begin function string__geq
	.p2align	2
	.type	string__geq,@function
string__geq:                    # @string__geq
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	sw	a1, 0(sp)
	call	strcmp
	not	a0, a0
	srli	a0, a0, 31
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end7:
	.size	string__geq, .Lfunc_end7-string__geq
	.cfi_endproc
                                        # -- End function
	.globl	print              # -- Begin function print
	.p2align	2
	.type	print,@function
print:                             # @print
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	mv	a1, a0
	sw	a0, 8(sp)
	lui	a0, %hi(.L.str)
	addi	a0, a0, %lo(.L.str)
	call	printf
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end8:
	.size	print, .Lfunc_end8-print
	.cfi_endproc
                                        # -- End function
	.globl	println            # -- Begin function println
	.p2align	2
	.type	println,@function
println:                           # @println
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	mv	a1, a0
	sw	a0, 8(sp)
	lui	a0, %hi(.L.str.1)
	addi	a0, a0, %lo(.L.str.1)
	call	printf
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end9:
	.size	println, .Lfunc_end9-println
	.cfi_endproc
                                        # -- End function
	.globl	printInt            # -- Begin function printInt
	.p2align	2
	.type	printInt,@function
printInt:                           # @printInt
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	mv	a1, a0
	sw	a0, 8(sp)
	lui	a0, %hi(.L.str.2)
	addi	a0, a0, %lo(.L.str.2)
	call	printf
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end10:
	.size	printInt, .Lfunc_end10-printInt
	.cfi_endproc
                                        # -- End function
	.globl	printlnInt         # -- Begin function printlnInt
	.p2align	2
	.type	printlnInt,@function
printlnInt:                        # @printlnInt
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	mv	a1, a0
	sw	a0, 8(sp)
	lui	a0, %hi(.L.str.3)
	addi	a0, a0, %lo(.L.str.3)
	call	printf
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end11:
	.size	printlnInt, .Lfunc_end11-printlnInt
	.cfi_endproc
                                        # -- End function
	.globl	getString           # -- Begin function getString
	.p2align	2
	.type	getString,@function
getString:                          # @getString
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	addi	a0, zero, 1024
	mv	a1, zero
	call	malloc
	mv	a1, a0
	sw	a0, 8(sp)
	lui	a0, %hi(.L.str)
	addi	a0, a0, %lo(.L.str)
	call	__isoc99_scanf
	lw	a0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end12:
	.size	getString, .Lfunc_end12-getString
	.cfi_endproc
                                        # -- End function
	.globl	getInt              # -- Begin function getInt
	.p2align	2
	.type	getInt,@function
getInt:                             # @getInt
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	lui	a0, %hi(.L.str.2)
	addi	a0, a0, %lo(.L.str.2)
	addi	a1, sp, 8
	call	__isoc99_scanf
	lw	a0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end13:
	.size	getInt, .Lfunc_end13-getInt
	.cfi_endproc
                                        # -- End function
	.globl	toString            # -- Begin function toString
	.p2align	2
	.type	toString,@function
toString:                           # @toString
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	addi	a0, zero, 20
	mv	a1, zero
	call	malloc
	lw	a2, 8(sp)
	sw	a0, 0(sp)
	lui	a1, %hi(.L.str.2)
	addi	a1, a1, %lo(.L.str.2)
	call	sprintf
	lw	a0, 0(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end14:
	.size	toString, .Lfunc_end14-toString
	.cfi_endproc
                                        # -- End function
	.globl	string__length    # -- Begin function string__length
	.p2align	2
	.type	string__length,@function
string__length:                   # @string__length
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	call	strlen
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end15:
	.size	string__length, .Lfunc_end15-string__length
	.cfi_endproc
                                        # -- End function
	.globl	string__substring # -- Begin function string__substring
	.p2align	2
	.type	string__substring,@function
string__substring:              # @string__substring
	.cfi_startproc
# %bb.0:
    addi	sp, sp, -32
    .cfi_def_cfa_offset 32
    sw	ra, 28(sp)
    .cfi_offset ra, -4
    sw	a0, 24(sp)
    sw	a1, 20(sp)
    sw	a2, 16(sp)
    sub	a0, a1, a0
    addi	a0, a0, 1
    srai	a1, a0, 31
    call	malloc
    lw	a1, 16(sp)
    lw	a2, 24(sp)
    lw	a3, 20(sp)
    sw	a0, 8(sp)
    add	a1, a1, a2
    sub	a2, a3, a2
    call	memcpy
    lw	a0, 20(sp)
    lw	a1, 24(sp)
    lw	a2, 8(sp)
    sub	a0, a0, a1
    slli	a0, a0, 2
    add	a0, a2, a0
    sw	zero, 0(a0)
    lw	a0, 8(sp)
    lw	ra, 28(sp)
    addi	sp, sp, 32
    ret
.Lfunc_end16:
	.size	string__substring, .Lfunc_end16-string__substring
	.cfi_endproc
                                        # -- End function
	.globl	string__parseInt  # -- Begin function string__parseInt
	.p2align	2
	.type	string__parseInt,@function
string__parseInt:                 # @string__parseInt
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	lui	a1, %hi(.L.str.2)
	addi	a1, a1, %lo(.L.str.2)
	addi	a2, sp, 4
	call	__isoc99_sscanf
	lw	a0, 4(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end17:
	.size	string__parseInt, .Lfunc_end17-string__parseInt
	.cfi_endproc
                                        # -- End function
	.globl	string__ord      # -- Begin function string__ord
	.p2align	2
	.type	string__ord,@function
string__ord:                     # @string__ord
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	a0, 12(sp)
	sw	a1, 8(sp)
	add	a0, a1, a0
	lb	a0, 0(a0)
	addi	sp, sp, 16
	ret
.Lfunc_end18:
	.size	string__ord, .Lfunc_end18-string__ord
	.cfi_endproc
                                        # -- End function
	.globl	array__size       # -- Begin function array__size
	.p2align	2
	.type	array__size,@function
array__size:                      # @array__size
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	a0, 8(sp)
	lw	a0, -4(a0)
	addi	sp, sp, 16
	ret
.Lfunc_end19:
	.size	array__size, .Lfunc_end19-array__size
	.cfi_endproc
                                        # -- End function
	.globl	ir_new_array   # -- Begin function ir_new_array
	.p2align	2
	.type	ir_new_array,@function
ir_new_array:                  # @ir_new_array
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -32
	.cfi_def_cfa_offset 32
	sw	ra, 28(sp)
	.cfi_offset ra, -4
	sw	a0, 20(sp)
	sw	a0, 20(sp)
	sw	a0, 20(sp)
	sw	a0, 20(sp)
	sw	a0, 20(sp)
	sw	a0, 20(sp)
	sw	a0, 20(sp)
	sw	a0, 20(sp)
	sw	a0, 20(sp)
	sw	a0, 20(sp)
	sw	a0, 20(sp)
	sw	a0, 20(sp)
	sw	a0, 20(sp)
	sw	a0, 20(sp)
	sw	a0, 20(sp)
	sw	a0, 20(sp)
	sw	a0, 20(sp)
	sw	a1, 16(sp)
	lw	a0, 0(a0)
	slli	a0, a0, 2
	bge	a0, a1, .LBB1_2
# %bb.1:
	sw	zero, 24(sp)
	j	.LBB1_6
.LBB1_2:
	lw	a0, 20(sp)
	lw	a1, 16(sp)
	add	a0, a0, a1
	lw	a0, 0(a0)
	sw	a0, 12(sp)
	slli	a0, a0, 2
	sw	a0, 8(sp)
	addi	a0, a0, 4
	srai	a1, a0, 31
	call	malloc
	lw	a1, 12(sp)
	addi	a2, a0, 4
	sw	a2, 4(sp)
	sw	a1, 0(a0)
	sw	zero, 0(sp)
.LBB1_3:                                # =>This Inner Loop Header: Depth=1
	lw	a0, 0(sp)
	lw	a1, 8(sp)
	bge	a0, a1, .LBB1_5
# %bb.4:                                #   in Loop: Header=BB1_3 Depth=1
	lw	a1, 16(sp)
	lw	a0, 20(sp)
	addi	a1, a1, 4
	call	ir_new_array
	lw	a1, 4(sp)
	lw	a2, 0(sp)
	add	a1, a1, a2
	sw	a0, 0(a1)
	lw	a0, 0(sp)
	addi	a0, a0, 4
	sw	a0, 0(sp)
	j	.LBB1_3
.LBB1_5:
	lw	a0, 4(sp)
	sw	a0, 24(sp)
.LBB1_6:
	lw	a0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.Lfunc_end21:
	.size	ir_new_array, .Lfunc_end21-ir_new_array
	.cfi_endproc
                                        # -- End function
	.type	.L.str,@object          # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"%s"
	.size	.L.str, 3

	.type	.L.str.1,@object        # @.str.1
.L.str.1:
	.asciz	"%s\n"
	.size	.L.str.1, 4

	.type	.L.str.2,@object        # @.str.2
.L.str.2:
	.asciz	"%d"
	.size	.L.str.2, 3

	.type	.L.str.3,@object        # @.str.3
.L.str.3:
	.asciz	"%d\n"
	.size	.L.str.3, 4

	.ident	"clang version 10.0.0-4ubuntu1 "
	.section	".note.GNU-stack","",@progbits
